import { useMemo } from "react";
import "./App.css";
import { ChallengeCard } from "./components/ChallengeCard";
import { useChallengeQueue } from "./hooks/useChallengeQueue";
import data from "./data/challenges.json";

function App() {
    const challenges = useMemo(() => {
        const arr = [...(data as any[])];
        for (let i = arr.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [arr[i], arr[j]] = [arr[j], arr[i]];
        }
        return arr;
    }, []);

    const {
        current,
        index,
        total,
        submitAnswer,
        next,
        result,
        streak,
        score,
        timeLeft,
        resetTimer,
    } = useChallengeQueue(challenges);

    if (!current) return <div className="container">Keine Daten 🤷‍♂️</div>;

    return (
        <div className="container">
            <header className="header">
                <h1>crazy_calc</h1>
                <div className="meta">
                    <span>Frage {index + 1}/{total}</span>
                    <span>🔥 Streak: {streak}</span>
                    <span>⭐ Score: {score}</span>
                    {typeof timeLeft === "number" && (
                        <span className={`timer ${timeLeft <= 5 ? "warn" : ""}`}>
              ⏱ {timeLeft}s
            </span>
                    )}
                </div>
            </header>

            <ChallengeCard
                key={current.id}
                challenge={current}
                onSubmit={(val) => submitAnswer(val)}
                result={result}
                onNext={() => { next(); }}
                onResetTimer={() => resetTimer()}
            />

            <footer className="footer">
                <small>
                    Mock-Daten – später ersetzt durch Backend (`/challenges/next`, `/answer`).
                </small>
            </footer>
        </div>
    );
}

export default App;
