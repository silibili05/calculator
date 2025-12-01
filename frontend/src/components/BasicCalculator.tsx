import { basicCalculate } from "../api/api";
import {useState} from "react";

export default function BasicCalculator() {
    const [a, setA] = useState(0);
    const [b, setB] = useState(0);
    const [op, setOp] = useState("ADD");
    const [result, setResult] = useState<number | null>(null);

    const calculate = async () => {
        const r = await basicCalculate(a, b, op);
        setResult(r.result);
    };

    return (
        <div className="card">
            <h2>Grundrechenarten</h2>
            <input type="number" value={a} onChange={(e) => setA(+e.target.value)} />
            <select value={op} onChange={(e) => setOp(e.target.value)}>
                <option value="ADD">+</option>
                <option value="SUBTRACT">−</option>
                <option value="MULTIPLY">×</option>
                <option value="DIVIDE">÷</option>
            </select>
            <input type="number" value={b} onChange={(e) => setB(+e.target.value)} />
            <button onClick={calculate}>Berechnen</button>
            {result !== null && <p>Ergebnis: {result}</p>}
        </div>
    );
}
