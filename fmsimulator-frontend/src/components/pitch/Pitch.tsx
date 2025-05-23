
import type { Role } from "../../types/team/lineup/Role";
import type { Starter } from "../../types/team/lineup/Starter";
import type { Team } from "../../types/team/Team";
import { extractStarterByRole } from "../../utils/TeamUtils";
import "./pitch_style.css";
import PitchMember from "./PitchMember";

function Pitch({team, kits}:{team: Team, kits: string}) {

    return (
        <div className="pitch">
            {team.roster.lineup.starters.map((starter:Starter, index:number) => 
                <PitchMember key={starter.requiredRole.stringValue} _role={starter.requiredRole} starter={starter} teamId={team.id} kits={kits}></PitchMember>
            )}
        </div>
    );
}
export default Pitch;