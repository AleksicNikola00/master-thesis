import { RouterProvider } from "react-router-dom";
import "./App.css";
import router from "./router";
import Header from "./components/Header";
import { QueryClientProvider } from "@tanstack/react-query";
import { queryClient } from "./config";

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <div className="flex flex-col">
        <Header />
        <div className="w-100% p-10 h-screen ">
          <RouterProvider router={router} />
        </div>
      </div>
    </QueryClientProvider>
  );
}

export default App;
