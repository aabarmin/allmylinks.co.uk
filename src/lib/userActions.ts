// currently returns a hardcoded value of 1
export async function getCurrentUserId(): Promise<number | null> {
  return Promise.resolve(1);
  // return Promise.resolve(null);
}