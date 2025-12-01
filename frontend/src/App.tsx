import {useState} from "react";
import BasicCalculator from "./components/BasicCalculator";
import Scientific from "./components/Scientific.tsx";
import StatisticsCalculator from "./components/StatisticsCalculator";
import HistoryView from "./components/HistoryView";
import "./styles.css";

export default function App() {
    const [tab, setTab] = useState("basic");

    return (
        <div className="wrapper">
            <h1>🧮 Taschenrechner – Microservices</h1>

            <div className="tabs">
                <button className={tab === "basic" ? "active" : ""} onClick={() => setTab("basic")}>
                    Grundrechner
                </button>
                <button className={tab === "scientific" ? "active" : ""} onClick={() => setTab("scientific")}>
                    Wissenschaftlich
                </button>
                <button className={tab === "statistics" ? "active" : ""} onClick={() => setTab("statistics")}>
                    Statistik
                </button>
                <button className={tab === "history" ? "active" : ""} onClick={() => setTab("history")}>
                    Verlauf
                </button>
            </div>

            {tab === "basic" && <BasicCalculator />}
            {tab === "scientific" && <Scientific />}
            {tab === "statistics" && <StatisticsCalculator />}
            {tab === "history" && <HistoryView />}
        </div>
    );
}
