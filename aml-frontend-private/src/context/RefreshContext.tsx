import { createContext, type ReactNode, useContext, useState } from "react";

interface RefreshContextType {
  refresh: () => void;
}

const RefreshContext = createContext<RefreshContextType | undefined>(undefined);

export const RefreshProvider = ({ children }: { children: ReactNode }) => {
  const [_, setRefreshKey] = useState(0);

  const refresh = () => {
    setRefreshKey((prevKey) => prevKey + 1);
  };

  return (
    <RefreshContext.Provider value={{ refresh }}>
      {children}
    </RefreshContext.Provider>
  );
};

export const useRefresh = () => {
  const context = useContext(RefreshContext);
  if (!context) {
    throw new Error("useRefresh must be used within a RefreshProvider");
  }
  return context;
};