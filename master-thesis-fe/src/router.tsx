import { createBrowserRouter } from "react-router-dom";
import { HomePage, PlayerDetailsPage } from "./pages";
import MainLayout from "./layout/MainLayout";

const router = createBrowserRouter([
  {
    path: "/",
    element: <MainLayout />, // Layout component
    children: [
      {
        index: true, // default route
        element: <HomePage />,
      },
      {
        path: "player/:id",
        element: <PlayerDetailsPage />,
      },
    ],
  },
]);

export default router;
