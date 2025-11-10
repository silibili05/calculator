import { useEffect, useMemo, useRef, useState } from "react";
import type { Challenge } from "../components/ChallengeCard";

export function useChallengeQueue(challenges: Challenge[]) {
    const [index, setIndex] = useState(0);
    const [result, setResult] = useState<null | "CORRECT" | "WRONG" | "EXPIRED">(null);
    const [streak, setStreak] = useState(0);
    const [score, setScore] = useState(0);
    const [timeLeft, setTimeLeft] = useState<number | null>(null);
    const timer = useRef<number | null>(null);

    const current = challenges[index];
    const total = challenges.length;

    const limit = useMemo(() => current?.timeLimitSec ?? 20, [current]);

    // Timer
    useEffect(() => {
        setResult(null);
        if (!current) return;
        setTimeLeft(limit);
        if (timer.current) window.clearInterval(timer.current);
        timer.current = window.setInterval(() => {
            setTimeLeft((t) => {
                if (t === null) return t;
                if (t <= 1) {
                    window.clearInterval(timer.current!);
                    setResult("EXPIRED");
                    setStreak(0);
                    return 0;
                }
                return t - 1;
            });
        }, 1000);
        return () => { if (timer.current) window.clearInterval(timer.current); };
    }, [index, current, limit]);

    const resetTimer = () => {
        if (!current) return;
        if (timer.current) window.clearInterval(timer.current);
        setTimeLeft(limit);
        timer.current = window.setInterval(() => {
            setTimeLeft((t) => (t && t > 0 ? t - 1 : 0));
        }, 1000);
    };

    const submitAnswer = (answer: string) => {
        if (!current) return;
        if (timeLeft === 0) { setResult("EXPIRED"); return; }

        const num = Number(answer.replace(",", "."));
        const correct =
            current.solution !== null &&
            Number.isFinite(num) &&
            Math.abs(num - Number(current.solution)) < 1e-9;

        if (correct) {
            setResult("CORRECT");
            setStreak((s) => s + 1);
            // simple scoring: difficulty & time bonus
            const base = current.difficulty === "HARD" ? 30 : current.difficulty === "MEDIUM" ? 20 : 10;
            const bonus = typeof timeLeft === "number" ? Math.max(0, Math.min(10, timeLeft)) : 0;
            setScore((sc) => sc + base + bonus);
        } else {
            setResult("WRONG");
            setStreak(0);
        }
    };

    const next = () => {
        setResult(null);
        setIndex((i) => (i + 1) % total);
    };

    return {
        current, index, total,
        submitAnswer, next, result,
        streak, score, timeLeft, resetTimer
    };
}
