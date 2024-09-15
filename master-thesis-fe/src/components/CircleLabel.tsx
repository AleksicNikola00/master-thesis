import { ReactElement } from "react";

type CircleLabelProps = {
  label: string;
  value: any;
};

export const CircleLabel = ({
  label,
  value,
}: CircleLabelProps): ReactElement => {
  return (
    <div
      className="shadow-[rgba(0,0,0,0.15)_0px_5px_15px_10px] 
    items-center justify-center relative flex w-32 h-32 rounded-full"
    >
      {value}
      <label className="opacity-65 absolute bottom-0">{label}</label>
    </div>
  );
};
