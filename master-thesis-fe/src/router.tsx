import { createBrowserRouter } from "react-router-dom";
import { ArticlePage, HomePage, PlayerDetailsPage } from "./pages";
import MainLayout from "./layout/MainLayout";

export const routes = {
  HOMEPAGE_PATH: "/",
  PLAYER_DETAILS_PATH: "player/:id",
  ARTICLE_PATH: "article",
};

const router = createBrowserRouter([
  {
    path: routes.HOMEPAGE_PATH,
    element: <MainLayout />, // Layout component
    children: [
      {
        index: true, // default route
        element: <HomePage />,
      },
      {
        path: routes.PLAYER_DETAILS_PATH,
        element: <PlayerDetailsPage />,
      },
      {
        path: routes.ARTICLE_PATH,
        element: <ArticlePage />,
      },
    ],
  },
]);

export default router;
