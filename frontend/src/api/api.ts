const BASE = "http://localhost:8085/api";

export interface BasicResponse {
    result: number;
}

export const basicCalculate = async (a: number, b: number, operation: string) => {
    const res = await fetch(`${BASE}/basic`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ a, b, operation }),
    });
    return res.json();
};

export interface ScientificResponse {
    result?: number;
    booleanResult?: boolean;
}

export const scientificCalculate = async (body: any) => {
    const res = await fetch(`${BASE}/scientific`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body),
    });
    return res.json();
};

export interface StatisticsResponse {
    mean: number;
    median: number;
    standardDeviation: number;
}

export const statisticsCalculate = async (values: number[]) => {
    const res = await fetch(`${BASE}/statistics`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ values }),
    });
    return res.json();
};

export interface HistoryRecord {
    type: string;
    expression: string;
    result: string;
    timestamp: string;
}

export const getHistory = async (): Promise<HistoryRecord[]> => {
    const res = await fetch(`${BASE}/history`);
    return res.json();
};

export const clearHistory = async () => {
    await fetch(`${BASE}/history`, { method: "DELETE" });
};
