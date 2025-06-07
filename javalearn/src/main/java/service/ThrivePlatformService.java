package service;

import dto.Developer;
import dto.Lead;
import dto.Project;
import dto.User;
import enums.ProjectCategory;
import enums.ProjectStatus;
import enums.UserType;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ThrivePlatformService {

    //using concurrent map for thread safety : slow but not extra code is required for synchronization
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Project> projects = new ConcurrentHashMap<>();

    public void registerLead(String leadId, String name) {
        if (users.containsKey(leadId)) {
            System.out.println("Lead already registered.");
            return;
        }
        Lead lead = new Lead(leadId, name);
        users.put(leadId, lead);
        System.out.println("Lead registered: " + name + " [" + leadId + "]");
    }

    //register developer
    public void registerDeveloper(String devId, String name) {
        if (users.containsKey(devId)) {
            System.out.println("Developer already registered.");
            return;
        }
        Developer dev = new Developer(devId, name);
        users.put(devId, dev);
        System.out.println("Developer registered: " + name + " [" + devId + "]");
    }

    //create project
    public void createProject(String projectId, String title, ProjectCategory category, String leadId) {
        if (projects.containsKey(projectId)) {
            System.out.println("Project ID already exists.");
            return;
        }
        User user = users.get(leadId);
        if (user == null || user.getType() != UserType.LEAD) {
            System.out.println("Lead not found.");
            return;
        }
        Project project = new Project(projectId, title, category, (Lead) user);
        projects.put(projectId, project);
        System.out.println("Project created: " + project);
    }


    //register developer to join project
    public void requestToJoinProject(String devId, String projectId) {
        User user = users.get(devId);
        Project project = projects.get(projectId);


        if (user == null || user.getType() != UserType.DEVELOPER) {
            System.out.println("Developer not found.");
            return;
        }

        if (project == null || project.getStatus() != ProjectStatus.OPEN && project.getStatus() != ProjectStatus.REQUESTED) {
            System.out.println("Project not available for request.");
            return;
        }

        attachProjectHistoryWithDev((Developer) user, projectId);

        //whenever developer register for project check whether it cancelled or not
        cancelProject(project);

        if (project.getStatus() == ProjectStatus.CANCELED) {
            System.out.println("Project is cancelled and can not requested.");
            return;
        }

        project.requestToJoin((Developer) user);
        System.out.println(" " + user.getName() + " requested to join project :" + project);
    }

    public List<Developer> getRegisteredDevelopers() {
        List<Developer> devs = users.entrySet().stream()
                .filter(x -> x.getValue().getType() == UserType.DEVELOPER)
                .map(x -> (Developer) x.getValue())
                .toList();
        return devs;
    }

    public List<Lead> getRegisteredLeads() {
        List<Lead> leads = users.entrySet().stream()
                .filter(x -> x.getValue().getType() == UserType.LEAD)
                .map(x -> (Lead) x.getValue())
                .toList();
        return leads;
    }


    private void attachProjectHistoryWithDev(Developer dev, String projectId) {
        Set<String> set = dev.getProjectIds();
        set.add(projectId);
    }

    /*
        1. Approve request - add and changes to status to assign to some developer : mark status to assigned status
     */
    public void approveRequest(String leadId, String projectId, String devId) {
        User lead = users.get(leadId);
        User dev = users.get(devId);
        Project project = projects.get(projectId);


        if (lead == null || lead.getType() != UserType.LEAD || project == null || !project.getLead().equals(lead)) {
            System.out.println("invalid lead or project.");
            return;
        }

        if (dev == null || dev.getType() != UserType.DEVELOPER || !project.hasRequested((Developer) dev)) {
            System.out.println("Developer did not request or not found.");
            return;
        }

        project.approve((Developer) dev);
        System.out.println(" Lead " + lead.getName() + " approved " + dev.getName() + " for projectId " + projectId);
    }

    //start project : status become in_progress
    public void startProject(String devId, String projectId) {
        User dev = users.get(devId);
        Project project = projects.get(projectId);

        if (dev == null || dev.getType() != UserType.DEVELOPER || !project.isAssignedTo((Developer) dev)) {
            System.out.println("Developer not authorized to start.");
            return;
        }

        project.start();
        System.out.println("Developer : " + dev.getName() + " started working on projectId " + projectId);
    }

    //cancel project assume - 10 sec is cancellation window

    private void cancelProject(Project project) {
        if (project.getStatus() == ProjectStatus.CANCELED) {
            System.out.println("Project already cancelled.");
            return;
        }

        //current time
        long currentTime = System.currentTimeMillis() / 1000;
        if ((currentTime - project.getStartTime()) > 10) {
            project.cancel();
        }
    }

    //using devId and projectId change status of project to complete
    public void completeProject(String devId, String projectId) {
        User dev = users.get(devId);
        Project project = projects.get(projectId);

        if (dev == null || dev.getType() != UserType.DEVELOPER || !project.isAssignedTo((Developer) dev)) {
            System.out.println("Developer not authorized to complete.");
            return;
        }

        project.complete();
        System.out.println("Developer " + dev.getName() + " completed projectId " + projectId);
    }
}