import { BrowserRouter, Routes, Route } from "react-router-dom";
import SelectSeat from "./pages/SelectSeat";
import ConfirmBooking from "./pages/ConfirmBooking";
import ConfirmFinalPage from "./pages/CompleteBooking";
import "./styles/global.css";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/select/:key" element={<SelectSeat />} />
                <Route path="/confirm/:key" element={<ConfirmBooking />} />
                <Route path="/complete/:key" element={<ConfirmFinalPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
