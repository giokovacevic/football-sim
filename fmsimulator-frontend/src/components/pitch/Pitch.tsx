
import type { IRole } from "../../types/models/team/lineup/Role";
import type { IStarter } from "../../types/models/team/lineup/Starter";
import type { ITeam } from "../../types/models/team/Team";
import { extractStarterByRole } from "../../utils/TeamUtils";
import "./pitch_style.css";
import PitchMember from "./PitchMember";

function Pitch({team, kits}:{team: ITeam, kits: string}) {

    return (
        <div className="pitch">
            {team.roster.lineup.starters.map((starter:IStarter, index:number) => 
                <PitchMember key={starter.requiredRole.stringValue} _role={starter.requiredRole} starter={starter} teamId={team.id} kits={kits}></PitchMember>
            )}
        </div>
    );
}
export default Pitch;