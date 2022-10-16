import { createRoot } from 'react-dom/client';
import { HelmetProvider } from 'react-helmet-async';
import { BrowserRouter } from 'react-router-dom';

import 'nprogress/nprogress.css';
import App from './App';
import { SidebarProvider } from './contexts/SidebarContext'
import * as serviceWorker from './serviceWorker';
const container = document.getElementById('root');
const root = createRoot(container!);

import Fetch from './hooks/CRUDService'

const userProps: any = Fetch.getAll('/users');
const user = userProps.data;


// @ts-ignore
root.render(
  <HelmetProvider>
    <SidebarProvider>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </SidebarProvider>
  </HelmetProvider>,
);

serviceWorker.unregister();