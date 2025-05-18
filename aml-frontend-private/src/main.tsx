import 'bootstrap/dist/css/bootstrap.min.css';
import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import {
  createHashRouter,
  RouterProvider
} from "react-router";
import BlockProperties from './BlockProperties';
import { RefreshProvider } from './context/RefreshContext';
import DashboardLayout from './DashboardLayout';
import DashboardPane from './DashboardPane';
import './main.css';
import { getBlock } from './service/BlockService';

const router = createHashRouter([
  {
    path: "/",
    Component: DashboardLayout,
    children: [
      { index: true, Component: DashboardPane },
      {
        path: "pages/:pageId",
        Component: DashboardPane,
        children: [
          {
            path: "blocks/:blockId",
            Component: BlockProperties,
            loader: async ({ params }) => {
              const blockId = Number(params.blockId) || 0;
              return await getBlock(blockId);
            }
          }
        ]
      }
    ]
  },
]);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RefreshProvider>
      <RouterProvider router={router} />
    </RefreshProvider>
  </StrictMode>,
)
