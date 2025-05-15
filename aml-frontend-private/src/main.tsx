import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {
  createHashRouter,
  RouterProvider,
} from "react-router";
import DashboardLayout from './DashboardLayout';
import 'bootstrap/dist/css/bootstrap.min.css';
import DashboardPane from './DashboardPane';
import { DashboardModel } from './model/DashboardModel';
import { DashboardProfile } from './model/DashboardProfile';

const router = createHashRouter([
  {
    path: "/",
    Component: DashboardLayout,
    children: [
      { 
        index: true, 
        Component: DashboardPane, 
        loader: () => {
          return new DashboardModel(
            new DashboardProfile(
              "1", 
              "12", 
              null, 
              null
            )
          )
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
