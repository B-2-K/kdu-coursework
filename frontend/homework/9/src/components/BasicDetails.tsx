import React from "react";
import "../scss/BasicDetails.css";

interface BasicDetailsProps {
    name: string;
    fullName: string;
    designation: string;
}

export const BasicDetails = ({
    name,
    fullName,
    designation,
}: BasicDetailsProps) => {
    return (
        <div className="basicDetails">
            <h1 className="name">{name}</h1>
            <h3>{fullName}</h3>
            <h3>{designation}</h3>
        </div>
    );
};