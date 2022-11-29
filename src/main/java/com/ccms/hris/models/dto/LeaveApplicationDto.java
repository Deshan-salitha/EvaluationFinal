package com.ccms.hris.models.dto;

import com.ccms.hris.enums.LeaveRequestStatus;
import com.ccms.hris.enums.LeaveType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeaveApplicationDto {
    private int leaveApplicationId;
    private LeaveType leaveType;
    private String reason;
    private LeaveRequestStatus leaveRequestStatus;
    private Long userId;
}
