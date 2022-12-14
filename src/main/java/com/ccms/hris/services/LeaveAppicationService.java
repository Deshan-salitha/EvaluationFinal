package com.ccms.hris.services;

import com.ccms.hris.enums.LeaveRequestStatus;
import com.ccms.hris.models.entities.LeaveApplication;
import com.ccms.hris.models.entities.User;
import com.ccms.hris.repositories.LeaveAllocationRepository;
import com.ccms.hris.repositories.LeaveApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveAppicationService {

    @Autowired
    LeaveApplicationRepository leaveApplicationRepository;

    @Autowired
    LeaveAllocationRepository leaveAllocationRepository;

    @Autowired
    UserService userService;



    public void createLeave(LeaveApplication leaveApplication){
        leaveApplicationRepository.save(leaveApplication);

    }
    public List<LeaveApplication> getAllLeaveAplications(){
        List<LeaveApplication> leaveApplications = leaveApplicationRepository.findAll();
        return leaveApplications;
    }

    public Optional<LeaveApplication> getLeaveApplicationById(int applicatonId){
        Optional<LeaveApplication> application = leaveApplicationRepository.findById(applicatonId);
        return application;
    }
    public void UpdateApplicationRequestStatus(LeaveApplication leaveApplication) throws Exception{
//        Optional<LeaveApplication> existLeaveApplication = leaveApplicationRepository.findById(leaveApplication.getLeaveApplicationId());
        leaveApplicationRepository.save(leaveApplication);
    }
//    public void rejectApplication(LeaveApplication leaveApplication)throws Exception{
//        Optional<LeaveApplication> leaveApplicationExist = leaveApplicationRepository.findById(leaveApplication.getLeaveApplicationId());
//
//        if(leaveApplicationExist.isEmpty()) throw new Exception("User does not exist");
//        if(leaveApplication.getLeaveRequestStatus() != null)  leaveApplicationExist.get().setLeaveRequestStatus(LeaveRequestStatus.REJECTED);
//        leaveApplicationRepository.save(leaveApplicationExist.get());
//    }
    public void deleteLeaveApplication(int id) throws Exception {

        Optional<LeaveApplication> application = leaveApplicationRepository.findById(id);

        if(application.isEmpty()) throw new Exception("User not found");
        else




        leaveApplicationRepository.deleteById(id);
    }
//    public void updateLeaveApplication(LeaveApplication leaveApplication) throws Exception {
//
//        Optional<LeaveApplication> leaveApplicationExist = leaveApplicationRepository.findById(leaveApplication.getLeaveApplicationId());
//
//        if(leaveApplicationExist.isEmpty()) throw new Exception("User does not exist");
//        if(leaveApplication.getLeaveType() != null)  leaveApplicationExist.get().setLeaveType(leaveApplication.getLeaveType());
//        userRepo.save(user);
//
//    }

}
