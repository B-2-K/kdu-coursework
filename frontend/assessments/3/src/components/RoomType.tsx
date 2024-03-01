import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { AppDispatch, RootState } from '../redux/store';
import { fetchRooms, Rooms, AddOns } from '../redux/fetchrooms';
import '../styles/RoomType.scss';
import SelectDates from './SelectDates';
import AddsOn from './AddsOn';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import BookRoom from './BookRoom';

export default function RoomType() {
    const dispatch = useDispatch<AppDispatch>();
    const fetchedRooms = useSelector((state: RootState) => state.rooms.roomTypes);
    const [selectedRoom, setSelectedRoom] = useState<Rooms | null>(null);
    const [selectedAddons, setSelectedAddons] = useState<AddOns[]>([]);
    const [startDate, setStartDate] = useState<Date | null>(null);
    const [endDate, setEndDate] = useState<Date | null>(null);

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
        }
        else {
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
        }
        else {
            element.style.border = '1px solid orange';
        }
    };

    const calculateTotalPrice = () => {
        if (!selectedRoom) return 0;

        const roomPrice = parseFloat(selectedRoom.costPerNight);
        const addonsPrice = selectedAddons.reduce((total, addon) => total + parseFloat(addon.cost), 0.0);

        console.log(roomPrice, addonsPrice);
        let numberOfNights = 1;
        try {
            numberOfNights = Math.ceil((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
            console.log(numberOfNights);
        }
        catch (e) {
            console.log(e);
        }
        
        let Price = (roomPrice + addonsPrice) * numberOfNights;
        console.log(Price);
        let gst = (Price * 18) / 100;
        let totalPrice = Price + gst;
    return totalPrice;
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
            <AddsOn />
            {selectedRoom && (
                <div className="selected-room-details">
                    <div className='rooms'>
                        {selectedRoom.addOns.map((addon) => (
                            <div
                                id={addon.name}
                                key={addon.name}
                                className={`addon ${selectedAddons.includes(addon) ? 'selected' : ''}`}
                                onClick={() => handleAddonSelection(addon)}
                            >
                                {addon.name}: {addon.cost} {addon.currency}
                            </div>
                        ))}
                    </div>
                    <SelectDates />
                    <div className="date-picker-container">
                        <div className="date-picker">
                            <DatePicker selected={startDate} onChange={(date) => setStartDate(date)} />
                        </div>
                        <div className="date-picker">
                            <DatePicker selected={endDate} onChange={(date) => setEndDate(date)} />
                        </div>
                    </div>
                    <div className="total-price">
                        <p>Cost + 18% GST = {calculateTotalPrice()} {selectedRoom.currency}</p>
                    </div>
                </div>
            )}
            {
            (startDate && endDate && selectedRoom ) ? <BookRoom /> : <div></div>
        }
        </div>
        
    );
}
