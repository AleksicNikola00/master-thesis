/**
 * Converts an UPPER_CASE string to camelCase.
 * @param str - The string in UPPER_CASE format.
 * @returns The string converted to camelCase.
 */
export const toCamelCase = (str: string): string =>
  str
    .toLowerCase() // Convert the entire string to lowercase
    .replace(/_./g, (match) => match.charAt(1).toUpperCase()); // Replace underscores followed by a character

export const toInnerTextHtml = (str: string): string => {
  return str.charAt(str.length - 1) === "." ? str : str + "...";
};

export const higlightWithMark = (str: string, query: string): string => {
  // Create a regular expression from the query, case insensitive and global match
  const regex = new RegExp(`(${query})`, "gi");

  // Replace the query with a <mark> tag around it
  const highlightedName = str.replace(regex, "<mark>$1</mark>");

  return highlightedName.replace(/\s/g, "&nbsp;");
};
