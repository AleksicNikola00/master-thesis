import { createBrowserRouter } from "react-router-dom";
import {
  ArticleDetailsPage,
  ArticlePage,
  HomePage,
  PlayerDetailsPage,
} from "./pages";
import MainLayout from "./layout/MainLayout";

export const routes = {
  HOMEPAGE_PATH: "/",
  PLAYER_DETAILS_PATH: "player/:id",
  ARTICLE_PATH: "article",
  ARTICLE_DETAILS_PATH: "article/:id",
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
      {
        path: routes.ARTICLE_DETAILS_PATH,
        element: <ArticleDetailsPage />,
      },
    ],
  },
]);

export default router;
