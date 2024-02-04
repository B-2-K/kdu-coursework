package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiscResponseDTO {
    private House house;
    private List<Room> rooms;
    private List<Device> devices;
}
