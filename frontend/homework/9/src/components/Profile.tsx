import React from "react";
import { BasicDetails } from "./BasicDetails";
import { Skills } from "./Skills";
import { Hobbies } from "./Hobbies";
import "../scss/Profile.css";

interface DetailsPageProps {
    data: {
        name: string;
        fullName: string;
        designation: string;
        skills: {
            id: number;
            skill: string;
        }[];
        hobbies: {
            id: number;
            hobby: string;
        }[];
    };
}

export const DetailsPage = ({ data }: DetailsPageProps) => {
    return (
        <div className="details-page">
            <BasicDetails
                name={data.name}
                fullName={data.fullName}
                designation={data.designation}
            />

            <div className="additional-details">
                <Skills skills={data.skills} />
                <Hobbies hobbies={data.hobbies} />
            </div>
        </div>
    );
};