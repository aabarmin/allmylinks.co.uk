import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {
  createHashRouter,
  RouterProvider,
} from "react-router";
import DashboardLayout from './DashboardLayout';
import 'bootstrap/dist/css/bootstrap.min.css';
import DashboardPane from './DashboardPane';
import { getDashboard } from './service/DashboardService';

const router = createHashRouter([
  {
    path: "/",
    Component: DashboardLayout,
    children: [
      {
        index: true,
        Component: DashboardPane,
        loader: async () => {
          return getDashboard();
        }
      }
    ]
  },
]);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
