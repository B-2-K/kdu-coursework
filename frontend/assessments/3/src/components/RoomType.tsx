import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { AppDispatch, RootState } from '../redux/store';
import { fetchRooms, Rooms, AddOns } from '../redux/fetchrooms';
import '../styles/RoomType.scss';
import SelectDates from './SelectDates';
import AddsOn from './AddsOn';
import 'react-datepicker/dist/react-datepicker-cssmodules.css';
import 'react-datepicker/dist/react-datepicker.css';
import BookRoom from './BookRoom';

export default function RoomType() {
    const dispatch = useDispatch<AppDispatch>();
    const fetchedRooms = useSelector((state: RootState) => state.rooms.roomTypes);
    const [selectedRoom, setSelectedRoom] = useState<Rooms | null>(null);
    const [selectedAddons, setSelectedAddons] = useState<AddOns[]>([]);
    const [startDate, setStartDate] = useState<string | null>(null);
    const [endDate, setEndDate] = useState<string | null>(null);

    useEffect(() => {
        dispatch(fetchRooms());
    }, [dispatch]);

    const handleRoomSelection = (room: Rooms) => {
        setSelectedRoom(room);
        setSelectedAddons([]);

        fetchedRooms.forEach((room) => {
            const element = document.getElementById(room.id);
            element.style.border = '1px solid gray';
        });

        const element = document.getElementById(room.id);
        if (element.style.border === '1px solid orange') {
            element.style.border = '1px solid gray';
        } else {
            element.style.border = '1px solid orange';
        }
    };

    const handleAddonSelection = (addon: AddOns) => {
        const updatedAddons = selectedAddons.includes(addon)
            ? selectedAddons.filter((selectedAddon) => selectedAddon !== addon)
            : [...selectedAddons, addon];
        setSelectedAddons(updatedAddons);
        const element = document.getElementById(addon.name);
        if (element.style.border === '1px solid orange') {
            element.style.border = '1px solid gray';
        } else {
            element.style.border = '1px solid orange';
        }
    };

    const calculateTotalPrice = () => {
        if (!selectedRoom) return 0;

        const roomPrice = parseFloat(selectedRoom.costPerNight);
        const addonsPrice = selectedAddons.reduce((total, addon) => total + parseFloat(addon.cost), 0.0);

        let numberOfNights = 1;
        try {
            const startDateObj = new Date(startDate);
            const endDateObj = new Date(endDate);
            numberOfNights = Math.ceil((endDateObj.getTime() - startDateObj.getTime()) / (24 * 60 * 60 * 1000));
        }
        catch (e) {
            console.log(e);
        }

        if (startDate == null || endDate == null) numberOfNights = 1;

        let Price = (roomPrice + addonsPrice) * numberOfNights;
        console.log(Price);
        let gst = (Price * 18) / 100;
        let totalPrice = Price + gst;
        return totalPrice;
    };

    const isValidDate = (date: string) => {
        const currentDate = new Date();
        const selectedDate = new Date(date);
        const yesterday = new Date();
        yesterday.setDate(currentDate.getDate() - 1);
        return selectedDate >= yesterday;
    };

    const isValidEndDate = (start: string, end: string) => {
        const startDateObj = new Date(start);
        const endDateObj = new Date(end);
        return endDateObj >= startDateObj;
    };

    return (
        <div className="rooms-details">
            
            <div className="room-heading">
                <div>Select Room Types</div>
            </div>
            <div className="rooms">
                {fetchedRooms.map((room) => (
                    <div
                        id={room.id}
                        key={room.id}
                        className={`single-room ${selectedRoom === room ? 'selected' : ''}`}
                        onClick={() => handleRoomSelection(room)}
                    >
                        {room.name}
                    </div>
                ))}
            </div>

            <SelectDates />
            <div className="date-picker-container">
                <div className="date-picker">
                    <input type="date" onChange={(e) => {
                        if (isValidDate(e.target.value)) {
                            setStartDate(e.target.value);
                        } else {
                            alert("Please select a valid check in date");
                        }
                    }} />
                </div>
                <div className="date-picker">
                    <input type="date" onChange={(e) => {
                        if (isValidEndDate(startDate!, e.target.value)) {
                            setEndDate(e.target.value);
                        } else {
                            alert("Please select a valid checkout date");
                        }
                    }} />
                </div>
            </div>

            <AddsOn />
            {selectedRoom && (
                <div className="selected-room-details">
                    <div className="rooms">
                        {selectedRoom.addOns.map((addon) => (
                            <div
                                id={addon.name}
                                key={addon.name}
                                className={`addon ${selectedAddons.includes(addon) ? 'selected' : ''}`}
                                onClick={() => handleAddonSelection(addon)}
                            >
                                {addon.name}
                            </div>
                        ))}
                    </div>

                    <div className="total-price">
                        <p>Cost + 18% GST = {calculateTotalPrice()} {selectedRoom.currency}</p>
                    </div>
                </div>
            )}

            {(startDate && endDate && selectedRoom) ? <BookRoom /> : <div className='rooms'> Please Select Room Types, Check-in and Check-out dates</div>}
        </div>
    );
}
