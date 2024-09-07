/**
 * Converts an UPPER_CASE string to camelCase.
 * @param str - The string in UPPER_CASE format.
 * @returns The string converted to camelCase.
 */
export const toCamelCase = (str: string): string =>
  str
    .toLowerCase() // Convert the entire string to lowercase
    .replace(/_./g, (match) => match.charAt(1).toUpperCase()); // Replace underscores followed by a character
