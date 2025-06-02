import { Navigate, useParams } from "react-router-dom";
import Club from "./Club";

const ClubWrapper = () => {
    const {clubId} = useParams<{clubId: string}>();

    return clubId ? (<Club clubId={clubId}></Club>) : (null)
}
export default ClubWrapper;