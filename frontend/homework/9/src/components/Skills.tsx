import React from "react";
import "../scss/Skills.css";

interface SkillsProps {
    skills: {
        id: number;
        skill: string;
    }[];
}

export const Skills = ({ skills }: SkillsProps) => {
    return (
        <div className="skills">
            <h1 className="heading">Skills</h1>
            <ul className="skill-list">
                {skills.map((skill) => (
                    <li key={skill.id}>{skill.skill}</li>
                ))}
            </ul>
        </div>
    );
};