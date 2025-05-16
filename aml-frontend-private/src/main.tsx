import 'bootstrap/dist/css/bootstrap.min.css';
import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import {
  createHashRouter,
  RouterProvider,
} from "react-router";
import DashboardLayout from './DashboardLayout';
import DashboardPane from './DashboardPane';

function Placeholder() {
  return (
    <div>Just placeholder</div>
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
            Component: Placeholder
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
