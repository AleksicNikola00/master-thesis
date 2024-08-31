import { createBrowserRouter } from "react-router-dom";
import { HomePage, PlayerDetailsPage } from "./pages";

const router = createBrowserRouter([
  {
    path: "/",
    element: <HomePage />,
  },
  {
    path: "/player/:id",
    element: <PlayerDetailsPage />,
  },
]);

export default router;
