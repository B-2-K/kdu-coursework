// import "./App.css";
import { DetailsPage } from "./components/Profile";

function App() {
    const response = {
        name: "Bittu",
        fullName: "Bittu Kumar",
        designation: "Software Developer Engineer",
        skills: [
            {
                id: 1,
                skill: "React Js",
            },
            {
                id: 2,
                skill: "Node Js",
            },
            {
                id: 3,
                skill: "Express Js",
            },
            {
                id: 4,
                skill: "MongoDB",
            },
            {
                id: 5,
                skill: "PostgreSQL",
            },
            {
                id: 6,
                skill: "AWS",
            },
            {
                id: 7,
                skill: "Java"
            },
            {
                id: 8,
                skill: "Springframework"
            }
        ],
        hobbies: [
            {
                id: 1,
                hobby: "cricket",
            },
            {
                id: 2,
                hobby: "music",
            }
        ],
    };
    return (
        <div className="app">
            <DetailsPage data={response} />
        </div>
    );
}

export default App;