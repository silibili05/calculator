import {useState} from "react";
import { getHistory, clearHistory } from "../api/api";

export default function HistoryView() {
    const [history, setHistory] = useState("");

    const load = async () => {
        const data = await getHistory();
        setHistory(
            data
                .map((r) => `[${r.type}] ${r.expression} = ${r.result} (${r.timestamp})`)
                .join("\n")
        );
    };

    const clear = async () => {
        await clearHistory();
        setHistory("Verlauf gelöscht.");
    };

    return (
        <div className="card">
            <h2>Verlauf</h2>
            <button onClick={load}>Laden</button>
            <button onClick={clear}>Löschen</button>
            <textarea value={history} readOnly rows={10} />
        </div>
    );
}
