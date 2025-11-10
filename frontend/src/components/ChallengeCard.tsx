import { useState } from "react";
import { AngerBadge } from "./AngerBadge";

export type Challenge = {
    id: string;
    question: string;
    solution: number | null;
    angerLevel: number; // 0..5
    message?: string;
    difficulty?: "EASY" | "MEDIUM" | "HARD" | string;
    timeLimitSec?: number;
    wrongAnswer: string;
};

type Props = {
    challenge: Challenge;
    result: null | "CORRECT" | "WRONG" | "EXPIRED";
    onSubmit: (answer: string) => void;
    onNext: () => void;
    onResetTimer?: () => void;
};

export const ChallengeCard = ({ challenge, result, onSubmit, onNext, onResetTimer }: Props) => {
    const [answer, setAnswer] = useState("");

    const bg =
        result === "CORRECT"
            ? "ok"
            : result === "WRONG"
                ? "bad"
                : result === "EXPIRED"
                    ? "expired"
                    : `anger-${Math.min(5, Math.max(0, challenge.angerLevel))}`;

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onSubmit(answer);
    };

    const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
        if (e.key === "Enter") {
            // Wenn es schon ein Ergebnis gibt → nächste Frage
            if (result) {
                e.preventDefault();
                onNext();
            }
        }
    };

    return (
        <div className={`card ${bg}`}>
            <div className="card-top">
                <AngerBadge level={challenge.angerLevel} />
                <span className="pill">{challenge.difficulty ?? "UNKNOWN"}</span>
                <span className="pill id">#{challenge.id}</span>
            </div>

            <h2 className="question">{challenge.question} = ?</h2>
            {challenge.message && <p className="msg">{challenge.message}</p>}

            <form className="row" onSubmit={handleSubmit}>
                <input
                    inputMode="decimal"
                    placeholder="Deine Antwort…"
                    value={answer}
                    onChange={(e) => setAnswer(e.target.value)}
                    onKeyDown={handleKeyDown} // 👈 hier passiert die Magie
                />
                <button type="submit">Check</button>
                <button type="button" className="ghost" onClick={onNext}>
                    Nächste →
                </button>
                {onResetTimer && (
                    <button
                        type="button"
                        className="ghost"
                        onClick={onResetTimer}
                        title="Timer neu starten"
                    >
                        ↻ Timer
                    </button>
                )}
            </form>

            {result && (
                <div className={`toast ${result.toLowerCase()}`}>
                    {result === "CORRECT" && "✅ Richtig! Weiter so 🔥"}
                    {result === "WRONG" && `❌ ${challenge.wrongAnswer}`}
                    {result === "EXPIRED" && "⌛ Zu spät! Nächste Chance…"}
                </div>
            )}
        </div>
    );
};
