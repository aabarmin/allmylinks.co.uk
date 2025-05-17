import 'bootstrap/dist/css/bootstrap.min.css';
import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import {
  createHashRouter,
  RouterProvider,
  useLoaderData,
} from "react-router";
import DashboardLayout from './DashboardLayout';
import DashboardPane from './DashboardPane';
import './main.css';
import type { BlockResponse } from './model/BlockModel';
import { getBlock } from './service/BlockService';

function Placeholder() {
  const data: BlockResponse = useLoaderData();

  return (
    <div>
      Just placeholder:
      <pre>
        {JSON.stringify(data)}
      </pre>
    </div>
  );
}

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
            Component: Placeholder,
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
    <RouterProvider router={router} />
  </StrictMode>,
)
