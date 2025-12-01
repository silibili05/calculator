import { scientificCalculate } from "../api/api";
import {useState} from "react";

export default function Scientific() {
    const [value, setValue] = useState(0);
    const [exp, setExp] = useState(2);
    const [op, setOp] = useState("POWER");
    const [result, setResult] = useState("");

    const calculate = async () => {
        const body: any = { value, operation: op };
        if (op === "POWER") body.exponent = exp;

        const r = await scientificCalculate(body);
        setResult(String(r.result ?? r.booleanResult));
    };

    return (
        <div className="card">
            <h2>Wissenschaftlich</h2>

            <input
                type="number"
                value={value}
                onChange={(e) => setValue(+e.target.value)}
            />

            <select value={op} onChange={(e) => setOp(e.target.value)}>
                <option value="POWER">Potenz</option>
                <option value="SQRT">Wurzel</option>
                <option value="FACTORIAL">Fakultät</option>
                <option value="IS_PRIME">Primzahl?</option>
            </select>

            {op === "POWER" && (
                <input
                    type="number"
                    value={exp}
                    onChange={(e) => setExp(+e.target.value)}
                />
            )}

            <button onClick={calculate}>Berechnen</button>

            {result && <p>Ergebnis: {result}</p>}
        </div>
    );
}
