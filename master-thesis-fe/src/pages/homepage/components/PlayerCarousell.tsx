export type CarousellPlayer = {
  id: number;
  label: string;
  value: any;
  fullName: string;
  imageUrl: string;
};

type PlayerCarousellProps = {
  title: string;
  players: CarousellPlayer[];
};

const PlayerCarousell = ({ title, players }: PlayerCarousellProps) => {
  return (
    <div className="flex flex-col gap-2">
      <div>{title}</div>
      <ul className="flex gap-5 justify-around">
        {players.map((player) => (
          <li key={player.id} className="flex flex-col items-center gap-2">
            <img width="100" src={player.imageUrl} alt="" />
            <label>{player.fullName}</label>
            <div className="flex gap-5">
              <label>{`${player.label}:`}</label>
              <label>{player.value}</label>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default PlayerCarousell;
