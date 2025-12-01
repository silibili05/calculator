import {useState} from "react";
import { statisticsCalculate } from "../api/api";

export default function StatisticsCalculator() {
    const [input, setInput] = useState("1,2,3,4,5");
    const [output, setOutput] = useState("");

    const calculate = async () => {
        const values = input.split(",").map((v) => Number(v.trim()));
        const r = await statisticsCalculate(values);
        setOutput(`Mittelwert: ${r.mean}, Median: ${r.median}, Standardabweichung: ${r.standardDeviation}`);
    };

    return (
        <div className="card">
            <h2>Statistik</h2>
            <input value={input} onChange={(e) => setInput(e.target.value)} />
            <button onClick={calculate}>Berechnen</button>
            <p>{output}</p>
        </div>
    );
}
