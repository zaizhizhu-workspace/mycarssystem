package com.example.mycarssystem.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 车辆数据传输对象
 */
@Data
public class CarsDTO {
    @NotBlank(message = "车辆名称不能为空")
    @Size(min = 2, max = 50, message = "车辆名称长度必须在2-50个字符之间")
    private String carName;
    
    @Size(max = 50, message = "车辆类型不能超过50个字符")
    private String carType;
    
    @NotBlank(message = "车架号不能为空")
    @Size(min = 17, max = 17, message = "车架号必须为17位")
    @Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "车架号格式不正确")
    private String vin;
    
    @NotBlank(message = "车牌号不能为空")
    @Pattern(regexp = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-HJ-NP-Z][A-HJ-NP-Z0-9]{4,5}[A-HJ-NP-Z0-9挂学警港澳]$|^[A-Z]{2}[A-HJ-NP-Z0-9]{4,5}[A-HJ-NP-Z0-9挂学警港澳]$", 
             message = "车牌号格式不正确")
    private String licensePlate;
    
    @NotBlank(message = "车主ID不能为空")
    private String ownerId;  // 前端只传ownerId
}
