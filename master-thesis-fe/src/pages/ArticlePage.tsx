import { Articles } from "../components";
import { Search } from "../components/Search";
import { useSearchParams } from "react-router-dom";
import { useQueryArticles } from "../service";

export const ArticlePage = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const query = searchParams.get("query");
  const { data: queriedArticles } = useQueryArticles(query);

  const onEnterHandler = (query: string) => {
    setSearchParams({ query });
  };

  return (
    <div className="flex flex-col gap-3 items-center w-100%">
      <div className="font-semibold self-start text-xl">Explore Articles:</div>
      <Search
        initialValue={query ?? ""}
        onEnter={onEnterHandler}
        placeholder="Search Articles"
      />
      {queriedArticles && <Articles articles={queriedArticles} />}
    </div>
  );
};
