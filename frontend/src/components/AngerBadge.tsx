export const AngerBadge = ({ level }: { level: number }) => {
    const labels = ["chill", "leicht salty", "genervt", "mad", "wütend", "AUSRASTEN"];
    const clamped = Math.min(5, Math.max(0, Math.round(level)));

    const emojis = "😶😐😒😠😡🤬";
    const emoji = emojis[clamped] || "😶";

    return (
        <span className={`anger anger-${clamped}`} title={`angerLevel: ${clamped}`}>
      <span className="emoji" style={{ fontSize: "1.2rem", marginRight: "4px" }}>
        {emoji}
      </span>
      <span className="anger-label">{labels[clamped]}</span>
    </span>
    );
};
