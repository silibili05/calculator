import React, { useState } from "react";
import "./App.css";

function App() {
    const [num1, setNum1] = useState<number>(0);
    const [num2, setNum2] = useState<number>(0);
    const [operation, setOperation] = useState<string>("ADD");
    const [result, setResult] = useState<number | null>(null);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        const response = await fetch("http://localhost:8085/api/basic", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({operation: operation, a: num1, b: num2 }),
        });
        const data = await response.json();
        setResult(data.result);
    };


    return (
        <div className="App">
            <h1>Rechner</h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="number"
                    value={num1}
                    onChange={e => setNum1(Number(e.target.value))}
                    placeholder="Zahl 1"
                />
                <select value={operation} onChange={e => setOperation(e.target.value)}>
                    <option value="ADD">+</option>
                    <option value="SUBSTRACT">-</option>
                    <option value="MULTIPLY">*</option>
                    <option value="DIVIDE">/</option>
                </select>
                <input
                    type="number"
                    value={num2}
                    onChange={e => setNum2(Number(e.target.value))}
                    placeholder="Zahl 2"
                />
                <button type="submit">Berechnen</button>
            </form>
            {result !== null && <p>Ergebnis: {result}</p>}
        </div>
    );
}

export default App;
