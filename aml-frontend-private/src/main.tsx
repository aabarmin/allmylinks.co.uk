import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {
  createHashRouter,
  RouterProvider,
} from "react-router";
import DashboardLayout from './DashboardLayout';
import 'bootstrap/dist/css/bootstrap.min.css';

const router = createHashRouter([
  {
    path: "/",
    Component: DashboardLayout,
    children: []
  },
]);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
