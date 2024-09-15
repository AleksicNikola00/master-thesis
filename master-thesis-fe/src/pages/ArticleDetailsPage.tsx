import { ReactElement } from "react";
import { useArticleDetails } from "../service";
import { useNavigate, useParams } from "react-router-dom";

export const ArticleDetailsPage = (): ReactElement => {
  const { id } = useParams();
  const navigate = useNavigate();

  const { data: articleDetails } = useArticleDetails(id);

  const onPlayerClickHandler = () => {
    navigate(`/player/${articleDetails?.playerId}`);
  };

  return (
    <div className="flex flex-col">
      <div className="2xl:px-60 xl:px-40 lg:px-24 md:px-20 sm:px-5 px-0">
        <div
          className="float-left mb-2 mr-3
      hover:cursor-pointer
      hover:opacity-95
      "
          onClick={onPlayerClickHandler}
        >
          <img width={350} src={articleDetails?.playerImageUrl} />
          <h2 className="text-xl font-semibold text-center mt-2">
            {`${articleDetails?.playerFirstName} ${articleDetails?.playerLastName}`}
          </h2>
        </div>

        <p>
          {articleDetails?.articleContent}{" "}
          <a
            className="underline text-blue-400"
            href={articleDetails?.articleUrl}
            target="_blank"
          >
            Read article from original source
          </a>
        </p>
      </div>
    </div>
  );
};
