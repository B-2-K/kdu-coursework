import Navbar from './Navbar';
import Main from './Main';
import '../styles/Home.scss';

export default function Home() {
    return (
        <div className="home">
            <Navbar />
            <Main />
        </div>
    );
}
