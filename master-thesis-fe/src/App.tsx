import { RouterProvider } from "react-router-dom";
import "./App.css";
import router from "./router";
import Header from "./components/Header";

function App() {
  return (
    <div className="flex flex-col">
      <Header />
      <div className="w-100% p-10 h-screen ">
        <RouterProvider router={router} />
      </div>
    </div>
  );
}

export default App;
