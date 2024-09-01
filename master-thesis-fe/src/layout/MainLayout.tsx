import { Outlet } from "react-router-dom";
import { Header } from "../components";

const MainLayout = () => {
  return (
    <div className="flex flex-col">
      <Header />
      <main className="w-100% p-10 h-screen ">
        <Outlet /> {/* Nested routes will be rendered here */}
      </main>
    </div>
  );
};

export default MainLayout;
