import dto.Developer;
import dto.Lead;
import enums.ProjectCategory;
import service.ThrivePlatformService;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThrivePlatformService platform = new ThrivePlatformService();

        platform.registerLead("L1", "Arpit");
        platform.registerLead("L2", "Aman");
        platform.registerDeveloper("D1", "Rahul");
        platform.registerDeveloper("D2", "Anjali");
        platform.registerDeveloper("D3", "Ayush");

        platform.createProject("P1", "Build Dashboard", ProjectCategory.FRONTEND, "L1");
        platform.requestToJoinProject("D1", "P1");
        platform.approveRequest("L1", "P1", "D1");
        platform.startProject("D1", "P1");
        platform.completeProject("D1", "P1");


        //cancellation scenario
        platform.createProject("P2", "Build Backend Services", ProjectCategory.BACKEND, "L2");

        Thread.sleep(12000);
        platform.requestToJoinProject("D2", "P2");


        platform.createProject("P3", "FrontEnd Services 2", ProjectCategory.FRONTEND, "L2");
        platform.requestToJoinProject("D1", "P3");
        platform.requestToJoinProject("D2", "P3");
        platform.requestToJoinProject("D3", "P3");
        platform.approveRequest("L2", "P3", "D3");

        platform.startProject("D3", "P3");
        platform.completeProject("D3", "P3");


        List<Lead> registeredLeads = platform.getRegisteredLeads();

        System.out.println(registeredLeads);


        List<Developer> registeredDevelopers = platform.getRegisteredDevelopers();

        System.out.println(registeredDevelopers);

    }
}