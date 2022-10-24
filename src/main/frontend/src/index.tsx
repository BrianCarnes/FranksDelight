import { createRoot } from 'react-dom/client';
import { HelmetProvider } from 'react-helmet-async';
import { BrowserRouter } from 'react-router-dom';

import 'nprogress/nprogress.css';
import App from './App';
import { SidebarProvider } from './contexts/SidebarContext'
import * as serviceWorker from './serviceWorker';
const container = document.getElementById('root');
const root = createRoot(container!);
import { CartProvider } from 'react-use-cart'
import {loadStripe} from '@stripe/stripe-js';
import { Elements } from '@stripe/react-stripe-js';
import { PUBLIC_KEY } from '../stripe_credentials.json';

const stripePromise = loadStripe(PUBLIC_KEY);
// @ts-ignore
root.render(
  <Elements stripe={stripePromise}>
  <CartProvider>
    <HelmetProvider>
      <SidebarProvider>
        <BrowserRouter>
          <App />
        </BrowserRouter>
      </SidebarProvider>
    </HelmetProvider>,
  </CartProvider>
  </Elements>
);

serviceWorker.unregister();