import { Articles } from "../components";
import { Search } from "../components/Search";
import { useTopArticles } from "../service";
import { useSearchParams } from "react-router-dom";

export const ArticlePage = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const query = searchParams.get("query") ?? "";

  const onEnterHandler = (query: string) => {
    setSearchParams({ query });
  };

  const { data: topArticles } = useTopArticles();
  return (
    <div className="flex flex-col gap-3 items-center w-100%">
      <div className="font-semibold self-start text-xl">Explore Articles:</div>
      <Search
        initialValue={query}
        onEnter={onEnterHandler}
        placeholder="Search Articles"
      />
      {topArticles && <Articles articles={topArticles} />}
    </div>
  );
};
