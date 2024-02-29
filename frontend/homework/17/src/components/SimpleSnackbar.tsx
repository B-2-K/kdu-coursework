import * as React from 'react';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/store';
import { closeSnackbar, openSnackbar } from '../redux/snackbarSlice';
import { useEffect } from 'react';

export default function CustomizedSnackbars() {
  const dispatch = useDispatch();

  const snackbarSliceValue = useSelector((state: RootState) => state.snackbar);

  const { error } = useSelector((state: RootState) => state.products);

  useEffect(() => {
    dispatch(openSnackbar());
  }, []);

  const handleClose = (event?: React.SyntheticEvent | Event, reason?: string) => {
    if (reason === 'clickaway') {
      return;
    }
    dispatch(closeSnackbar());
  };

  return (
    <div>
      <Snackbar open={snackbarSliceValue.open} autoHideDuration={6000} onClose={handleClose}>
        <div style={{
          position: 'fixed',
          bottom: '20px',
          left: '50%',
          transform: 'translateX(-50%)',
          zIndex: 9999 // Adjust z-index if necessary to ensure it's above other content
        }}>
          {error ? (
            <Alert
              onClose={handleClose}
              severity="error"
              variant="filled"
              sx={{ width: '100%' }}
            >
              Failed to fetch product details.
            </Alert>
          ) : (
            <Alert
              onClose={handleClose}
              severity="success"
              variant="filled"
              sx={{ width: '100%' }}
            >
              Product details are fetched successfully
            </Alert>
          )}
        </div>
      </Snackbar>
    </div>
  );
}