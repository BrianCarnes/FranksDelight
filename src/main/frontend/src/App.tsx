import { useRoutes } from 'react-router-dom';
import router from '../src/router';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers';
import CssBaseline from '@mui/material/CssBaseline';
import ThemeProvider from './theme/ThemeProvider';


function App() {
  const content = useRoutes(router);

  // @ts-ignore
  return (
    <ThemeProvider>
      <LocalizationProvider dateAdapter={AdapterDateFns}>
        <CssBaseline />
        {content}
      </LocalizationProvider>
    </ThemeProvider>
  );
}
export default App;