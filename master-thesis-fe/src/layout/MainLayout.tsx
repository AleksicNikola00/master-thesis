import { Outlet } from "react-router-dom";
import { Header } from "../components";

const MainLayout = () => {
  return (
    <div className="flex flex-col 2xl:px-80 xl:px-50 lg:px-32 md:px-20 sm:px-5 px-0">
      <Header />
      <main className="w-100% py-10 h-screen ">
        <Outlet /> {/* Nested routes will be rendered here */}
      </main>
    </div>
  );
};

export default MainLayout;
