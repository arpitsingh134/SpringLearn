package dto;

import enums.ProjectCategory;
import enums.ProjectStatus;

import java.util.HashSet;
import java.util.Set;

public class Project {
    private final String id;
    private final String title;
    private final ProjectCategory category;
    private ProjectStatus status;
    private final Lead lead;
    private Developer assignedDeveloper;
    private long startTime;
    private final Set<Developer> requestedDevelopers;

    public Project(String id, String title, ProjectCategory category, Lead lead) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.lead = lead;
        this.status = ProjectStatus.OPEN;
        this.startTime = System.currentTimeMillis() / 1000;
        this.requestedDevelopers = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public Lead getLead() {
        return lead;
    }


    public long getStartTime() {
        return startTime;
    }

    public void requestToJoin(Developer dev) {
        System.out.println("Project by requested by dev : " + dev);
        requestedDevelopers.add(dev);
        status = ProjectStatus.REQUESTED;
    }

    public boolean hasRequested(Developer dev) {
        return requestedDevelopers.contains(dev);
    }

    public void approve(Developer dev) {
        assignedDeveloper = dev;
        status = ProjectStatus.ASSIGNED;
    }

    public boolean isAssignedTo(Developer dev) {
        return dev.equals(assignedDeveloper);
    }

    public void start() {
        status = ProjectStatus.IN_PROGRESS;
    }

    public void cancel() {
        status = ProjectStatus.CANCELED;
    }

    public void complete() {
        status = ProjectStatus.COMPLETED;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", status=" + status +
                ", lead=" + lead +
                ", assignedDeveloper=" + assignedDeveloper +
                '}';
    }
}