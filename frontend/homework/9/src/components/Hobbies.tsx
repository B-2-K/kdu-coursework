import React from "react";
import "../scss/Hobbies.css";

interface HobbiesProps {
    hobbies: {
        id: number;
        hobby: string;
    }[];
}

export const Hobbies = ({ hobbies }: HobbiesProps) => {
    return (
        <div className="hobbies">
            <h1 className="heading">Hobbies</h1>
            <ul className="hobby-list">
                {hobbies.map((hobby) => (
                    <li key={hobby.id}>{hobby.hobby}</li>
                ))}
            </ul>
        </div>
    );
};